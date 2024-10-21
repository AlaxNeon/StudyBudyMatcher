import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

export const registerUser = async (userData) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/users/register`, userData);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const loginUser = async (loginData) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/users/login`, loginData);
        return response.data;
    } catch (error) {
        throw error;
    }
};

// Add more functions to interact with other endpoints as needed
