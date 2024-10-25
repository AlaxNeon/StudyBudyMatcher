import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import "../styles/ProfilePage.css";

function ProfilePage() {
  const [profile, setProfile] = useState(null);
  const [friends, setFriends] = useState([]);
  const [interests, setInterests] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const user = JSON.parse(localStorage.getItem("user"));
  const userId = user?.userId;
  const userFullName = user?.fullName?.split(" ")[0];

  useEffect(() => {
    if (!userId) {
      setError("User not logged in or userId is missing.");
      setLoading(false);
      return;
    }

    // Fetch profile data
    axios
      .get(`http://localhost:8080/api/profiles/user/${userId}`)
      .then((response) => {
        console.log("Profile data:", response.data); // Log the profile data
        setProfile(response.data);
        return axios.get(
          `http://localhost:8080/api/user-interests/user/${userId}`
        );
      })
      .then((response) => {
        setInterests(
          response.data.map((interest) => interest.interest.interestName)
        );
        setLoading(false);
      })
      .catch((error) => {
        setError("Failed to load profile or interests.");
        setLoading(false);
      });

    // Fetch friends list
    axios
      .get(`http://localhost:8080/api/friends/user/${userId}`)
      .then((response) => setFriends(response.data))
      .catch((error) => console.error("Error fetching friends:", error));
  }, [userId]);

  // Handle delete account
  const handleDeleteAccount = () => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete your account permanently?"
    );

    if (confirmDelete) {
      const secondConfirm = window.confirm("Are you 100% sure?");
      if (secondConfirm) {
        axios
          .delete(`http://localhost:8080/api/users/${userId}`)
          .then(() => {
            alert("Your account has been deleted.");
            localStorage.removeItem("user"); // Clear localStorage
            navigate("/"); // Redirect to home page
          })
          .catch((error) => {
            console.error("Error deleting account:", error);
            alert("There was an issue deleting your account.");
          });
      }
    }
  };

  // Handle clicking on a friend's name
  const handleFriendClick = (friend) => {
    if (friend.meetLink) {
      window.open(friend.meetLink, "_blank"); // Open the friend's meeting link
    } else {
      alert(`${friend.fullName} has no meeting link provided.`);
    }
  };

  if (loading) {
    return <p>Loading profile...</p>;
  }

  if (error) {
    return <p>Error: {error}</p>;
  }

  if (!profile) {
    return <p>Profile not found or incomplete data.</p>;
  }

  console.log("Meet Link for user:", profile?.user?.meetLink); // Log the meet link

  return (
    <div className="profile-page">
      <h1 className="welcomeUser">Welcome, {userFullName || "User"}</h1>

      {/* Display Bio */}
      <p className="profile-bio">
        <b className="profile-bio-title">Bio:</b> {profile.bio || "No bio provided"}
      </p>

      {/* Display Location */}
      <p className="profile-location">
        <b className="profile-location-title">Location:</b> {profile.country?.countryName || "Location not provided"}
      </p>

      {/* Display Interests */}
      <p className="userInterests">
        <b className="userInterests-title">Interests:</b>{" "}
        {interests.length > 0 ? interests.join(", ") : "No interests listed"}
      </p>

      {/* My Meet Room Button */}
      {profile.meetLink ? (
        <button
          className="meet-room-button"
          onClick={() => window.open(profile.meetLink, "_blank")}
        >
          My Meeting Room
        </button>
      ) : (
        <p>No meeting link provided for your profile.</p>
      )}

      <div className="friends-list">
        <h2>Friends</h2>
        {friends.length > 0 ? (
          <ul>
            {friends.map((friend) => (
              <li key={friend.friendshipId}>
                <span
                  className="friends-list-names"
                  onClick={() => handleFriendClick(friend.friend)}
                >
                  {friend.friend.fullName}
                </span>
              </li>
            ))}
          </ul>
        ) : (
          <>
            <p>No friends yet. Connect with people around you!</p>
            <Link to="/friends" className="connect-friends-button">
              Connect to Friends
            </Link>
          </>
        )}
      </div>

      {/* Delete Account Button */}
      <button className="delete-account-button" onClick={handleDeleteAccount}>
        Delete Account
      </button>
    </div>
  );
}

export default ProfilePage;