import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/FriendsPage.css';

function FriendsPage() {
    const [people, setPeople] = useState([]);
    const [connectedUsers, setConnectedUsers] = useState(new Set());
    const [searchQuery, setSearchQuery] = useState(""); // New state for the search query

    // Retrieve userId from local storage
    const userId = JSON.parse(localStorage.getItem('user'))?.userId;

    useEffect(() => {
        if (!userId) return; // Ensure userId is available

        // Fetch users and friends data
        axios.get('http://localhost:8080/api/users')
            .then(response => {
                const filteredPeople = response.data.filter(person => person.userId !== userId);
                setPeople(filteredPeople);
            })
            .catch(error => console.error('Error fetching people:', error));

        // Fetch connected friends
        axios.get(`http://localhost:8080/api/friends/user/${userId}`)
            .then(response => {
                const connected = new Set(response.data.map(friend => friend.friend.userId));
                setConnectedUsers(connected);
            })
            .catch(error => console.error('Error fetching connected users:', error));
    }, [userId]);

    const handleConnect = (friendId) => {
        // Handle connecting with a person
        axios.post('http://localhost:8080/api/friends/add', {
            userId: userId,  // Logged-in user's ID
            friendId: friendId  // The selected friend's ID
        })
        .then(response => {
            alert('Friend added successfully!');
            setConnectedUsers(prevSet => new Set(prevSet).add(friendId)); // Add connected friend
        })
        .catch(error => {
            console.error('Error adding friend:', error);
            alert('Error connecting with user.');
        });
    };

    // Filter out already connected users
    const unconnectedPeople = people.filter(person => !connectedUsers.has(person.userId));

    // Filter the unconnectedPeople based on the search query
    const filteredPeople = unconnectedPeople.filter(person =>
        person.fullName.toLowerCase().includes(searchQuery.toLowerCase())
    );

    return (
        <div className="friends-page">
            {/* Conditionally render the heading and search box */}
            {unconnectedPeople.length > 0 && (
                <>
                    <h1 className="heading">Find Your Study Buddy:</h1>
                    <input
                        type="text"
                        placeholder="Search your buddy"
                        className="search-box"
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                    />
                </>
            )}

            {filteredPeople.length > 0 ? (
                <div className="people-list">
                    {filteredPeople.map(person => (
                        <div key={person.userId} className="person-card">
                            <p className="fullName">{person.fullName}</p>
                            {/* Display interests if available */}
                            <p className="interest">{Array.isArray(person.interests) ? person.interests.join(', ') : 'No interests listed'}</p>
                            <button className="connect-button"
                                onClick={() => handleConnect(person.userId)}
                                disabled={connectedUsers.has(person.userId)}  // Disable button if already connected
                            >
                                {connectedUsers.has(person.userId) ? "Connected" : "Connect"}
                            </button>
                        </div>
                    ))}
                </div>
            ) : (
                searchQuery ? (
                    <h2 className="no-friends-message">Can't find your study buddy</h2>
                ) : (
                    <h2 className="no-friends-message">You Have connected to everyone present here!!</h2>
                )
            )}
        </div>
    );
}

export default FriendsPage;
