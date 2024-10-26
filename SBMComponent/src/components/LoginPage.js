import React, { useState } from 'react';
import axios from 'axios';
import '../styles/LoginPage.css';

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/api/users/login', { email, password })
            .then(response => {
                // Store user data in local storage or state
                localStorage.setItem('user', JSON.stringify(response.data));
                alert('Login successful!');
                window.location.href = '/profile'; // Redirect to profile page
            })
            .catch(error => {
                setErrorMessage('Incorrect email or password.');
                console.error('Login error:', error);
            });
    };

    return (
        <div className="login-page">
            <div class="login-form">
            <h1>Login</h1>
            {errorMessage && <p className="error">{errorMessage}</p>}
            <form onSubmit={handleSubmit}>
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                <button type="submit" className="btn">Login</button>
            </form>
            </div>
        </div>
    );
}

export default LoginPage;
