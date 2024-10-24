import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/HomePage.css';

function HomePage() {
    return (
        <div className="home-page">
            <div class="home-page-form">
            <h1>WELCOME TO STUDY BUDDY</h1>
            <p>Collaborate, learn, and succeed together.</p>
            <Link to="/login" className="btn">Login</Link>
            <Link to="/register" className="btn">Register</Link>
            </div>
        </div>
    );
}

export default HomePage;