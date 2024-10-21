import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/HomePage.css';

function HomePage() {
    return (
        <div className="home-page">
            <div class="home-page-form">
            <h1>Welcome to Eco2M</h1>
            <p>We are here to unite individuals who share a common passion for sustainability and environmental stewardship.</p>
            <Link to="/login" className="btn">Login</Link>
            <Link to="/register" className="btn">Register</Link>
            </div>
        </div>
    );
}

export default HomePage;