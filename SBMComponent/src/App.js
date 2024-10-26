import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import HomePage from './components/HomePage';
import RegisterPage from './components/RegisterPage';
import LoginPage from './components/LoginPage';
import ProfilePage from './components/ProfilePage';
import FriendsPage from './components/FriendsPage';
import AboutUsPage from './components/AboutUsPage';
// import ContactUsPage from './components/ContactUsPage';
import './styles/App.css';

function App() {
    return (
        <Router>
            <div className="App">
                <Header />
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/register" element={<RegisterPage />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/profile" element={<ProfilePage />} />
                    <Route path="/friends" element={<FriendsPage />} />
                    <Route path="/about" element={<AboutUsPage />} />
                    {/* <Route path="/contact" element={<ContactUsPage />} /> */}
                </Routes>
            </div>
        </Router>
    );
}

export default App;
