import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import '../styles/ChatPage.css';

function ChatPage() {
    const [chats, setChats] = useState([]);

    // Retrieve userId from local storage
    const userId = JSON.parse(localStorage.getItem('user'))?.userId;

    useEffect(() => {
        if (!userId) return; // Ensure userId is available

        // Fetch the list of friends (connected users)
        axios.get(`http://localhost:8080/api/friends/user/${userId}`)
            .then(response => {
                setChats(response.data); // Friends data
            })
            .catch(error => console.error('Error fetching chats:', error));
    }, [userId]);

    return (
        <div className="chat-page">
            <h1 className="chat-page-heading">Chats available for you...</h1>
            <div className="chat-list">
                {chats.length > 0 ? (
                    chats.map(chat => (
                        <div key={chat.friendId} className="chat-card">
                            <Link to={`/chat/${chat.friend.userId}`}>
                                <button className="chat-button">
                                    {chat.friend.fullName}
                                </button>
                            </Link>
                        </div>
                    ))
                ) : (
                    <div className="no-friends-section">
                        <p>No friends yet. Connect with people around you!</p>
                        <Link to="/friends">
                            <button className="connect-friends-button">
                                Connect to Friends
                            </button>
                        </Link>
                    </div>
                )}
            </div>
        </div>
    );
}

export default ChatPage;
