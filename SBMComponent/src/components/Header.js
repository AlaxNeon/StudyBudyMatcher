import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "../styles/Header.css";
import Eco2MLogo from "../assets/SBMLogo.png"; // Adjust the path according to where your logo image is stored

function Header() {
  const user = JSON.parse(localStorage.getItem("user")); // Retrieve user data from local storage
  const navigate = useNavigate(); // Replaces useHistory

  // Function to handle logout
  const handleLogout = () => {
    localStorage.removeItem("user"); // Remove user from local storage
    navigate("/"); // Redirect to the home page
    window.location.reload(); // Optionally refresh the page to reset the UI state
  };

  return (
    <header className="sbm-header">
      <div className="logo-container">
        <Link to="/">
          <img src={Eco2MLogo} alt="SBM Logo" className="logo" />
        </Link>
      </div>
      <nav className="nav-links">
        <Link to="/" className="nav-link">
          Home
        </Link>

        {user ? (
          <>
            <Link to="/profile" className="nav-link">
              Profile
            </Link>
           <Link to="/friends" className="nav-link">
              Search
            </Link>
          
            <Link to="/about" className="nav-link">
              About Us
            </Link>

            <button onClick={handleLogout} className="header-button">
              Logout
            </button>
          </>
        ) : (
          <>
            <Link to="/about" className="nav-link">
              About Us
            </Link>
            <Link to="/login" className="header-button">
              Login
            </Link>
            <Link to="/register" className="header-button">
              Register
            </Link>
          </>
        )}
      </nav>
    </header>
  );
}

export default Header;
