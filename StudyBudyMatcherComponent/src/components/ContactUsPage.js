import React from 'react';
import '../styles/ContactUsPage.css';

function ContactUsPage() {
    return (
        <div className="contact-us-page">
            <div className="contact-box">
                <h1>Contact Us</h1>
                <p>We’d love to hear from you! Whether you have questions, feedback, or just want to get in touch, our team is here to help.</p>
                <p>Email Us: For general inquiries, please email us at <a href="mailto:ecomatchmaker@gmail.com">ecomatchmaker@gmail.com</a></p>
                <p>Follow Us on Social Media:</p>
                <ul>
                    <li><a href="https://www.facebook.com/profile.php?id=61565564490812" target="_blank" rel="noopener noreferrer">Facebook</a></li>
                    <li><a href="https://x.com/EcoMatchMaker9" target="_blank" rel="noopener noreferrer">Twitter</a></li>
                    <li><a href="https://www.instagram.com/ecomatchmaker/" target="_blank" rel="noopener noreferrer">Instagram</a></li>
                </ul>
            </div>
        </div>
    );
}

export default ContactUsPage;
