import React from 'react';
import '../styles/AboutUsPage.css';

function AboutUsPage() {
    return (
        <div className="about-us-page">
            <div className="text-box-1">
                <h1>About Us</h1>
                <p>
                Welcome to Study Buddy! 
                We are passionate about connecting students with the resources they need to excel in their studies. 
                Whether you’re preparing for exams, working on assignments, or collaborating with peers, 
                Study Buddy is here to provide the tools and support to enhance your learning experience.
                </p>
                <p>
                At Study Buddy, our mission is to make learning easier and more accessible.
                 We strive to create a community where students can share knowledge, 
                find study materials, and track their academic progress in a fun and engaging way.
                </p>
            </div>
            <div className="text-box-2">
            <h1>Contact Us</h1>
                <p>We’d love to hear from you! If you have any questions or need assistance, feel free to reach out to us!</p>
                <p>Email Us: For general inquiries, please email us at <a href="mailto:support@studybuddy.com.">support@studybuddy.com</a></p>
            </div>
        </div>
    );
}
export default AboutUsPage;
