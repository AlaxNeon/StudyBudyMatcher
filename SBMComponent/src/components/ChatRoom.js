import React, { useState, useEffect, useRef } from "react";
import { useParams, useNavigate } from "react-router-dom"; // Added useNavigate
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";
import axios from "axios";
import '../styles/ChatRoom.css';

let stompClient = null;

function ChatRoom() {
  const { friendId } = useParams();
  const [messages, setMessages] = useState([]);
  const [inputMessage, setInputMessage] = useState("");
  const messageEndRef = useRef(null);
  const [isConnected, setIsConnected] = useState(false);
  const [userFullName, setUserFullName] = useState("");
  const [friendFullName, setFriendFullName] = useState("");
  const navigate = useNavigate(); // For programmatic navigation

  const userId = parseInt(JSON.parse(localStorage.getItem("user"))?.userId, 10);

  useEffect(() => {
    if (!userId) return;

    // Fetch user full name
    axios.get(`http://localhost:8080/api/users/${userId}`)
      .then(response => {
        setUserFullName(response.data.fullName);
      })
      .catch(error => console.error('Error fetching user full name:', error));

    // Fetch friend's full name
    axios.get(`http://localhost:8080/api/friends/user/${userId}`)
      .then(response => {
        const friend = response.data.find(f => f.friend.userId === parseInt(friendId));
        if (friend) {
          setFriendFullName(friend.friendFullName);
        }
      })
      .catch(error => console.error('Error fetching friend full name:', error));
  
    connect();
  
    return () => {
      if (stompClient) {
        stompClient.disconnect(() => {
          setIsConnected(false);
          console.log("Disconnected");
        });
      }
    };
  }, [userId, friendId]);

  const connect = () => {
    const socket = new SockJS("http://localhost:8080/ws");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
  };

  const onConnected = () => {
    setIsConnected(true);
    stompClient.subscribe(`/topic/public`, onMessageReceived);
  };

  const onError = (error) => {
    console.error("WebSocket error:", error);
    setIsConnected(false);
  };

  const handleSendMessage = () => {
    if (!inputMessage.trim()) {
      return;  // Prevent sending empty messages
    }

    if (!isConnected) {
      alert("Not connected to the chat server.");
      return;
    }

    const chatMessage = {
      sender: userId,
      content: inputMessage,
      timestamp: new Date().toLocaleTimeString(),
    };

    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
    setInputMessage("");
  };

  const scrollToBottom = () => {
    messageEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  const onMessageReceived = (payload) => {
    const message = JSON.parse(payload.body);
    setMessages((prevMessages) => [...prevMessages, message]);
    scrollToBottom();
  };

  // Custom leave confirmation popup
  const handleLeaveChat = () => {
    const confirmLeave = window.confirm(
      "The chat Database is under Development.\n" +
      "Hence your current chats are not saved and cannot be retrieved in the future.\n" +
      "We are very sorry for the inconvenience.\n\n" +
      "Are you sure you want to leave?"
    );
    if (confirmLeave) {
      navigate('/profile'); // Replace with the desired navigation route on "Leave"
    }
  };

  return (
    <div className="chat-room">
      <h1 className="chat-room-heading"><center>Chat with {friendFullName || "Loading..."}</center></h1>
      <div className="message-area">
        {messages.map((msg, index) => {
          const isSentMessage = parseInt(msg.sender, 10) === userId;
          return (
            <div
              key={index}
              className={`message ${isSentMessage ? "sent-message" : "received-message"}`}
            >
              <span>{isSentMessage ? "YOU" : friendFullName}: {msg.content}</span>
            </div>
          );
        })}
        <div ref={messageEndRef} />
      </div>
      <div className="input-area">
        <input
          className="input-area-input"
          type="text"
          value={inputMessage}
          onChange={(e) => setInputMessage(e.target.value)}
          onKeyPress={(e) => e.key === "Enter" && handleSendMessage()}
          placeholder="Type a message..."
        />
        <button
          className="input-area-button"
          onClick={handleSendMessage}
          disabled={!isConnected}
        >
          Send
        </button>
      </div>
      <div className="leave-button-area">
        <button
          className="leave-button"
          onClick={handleLeaveChat}
        >
          Leave Chat
        </button>
      </div>
    </div>
  );
}

export default ChatRoom;
