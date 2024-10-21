import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/RegisterPage.css";

function RegisterPage() {
  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: "",
    confirmPassword: "",
    interests: [],
    countryId: "",
    bio: "",
  });

  const [interestsOptions, setInterestsOptions] = useState([]);
  const [countries, setCountries] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [charCount, setCharCount] = useState(0);
  const [selectedCountry, setSelectedCountry] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/interests/")
      .then((response) => {
        setInterestsOptions(response.data);
      })
      .catch((error) => {
        console.error("Error fetching interests:", error);
      });

    axios
      .get("http://localhost:8080/api/countries")
      .then((response) => {
        setCountries(response.data);
      })
      .catch((error) => {
        console.error("Error fetching countries:", error);
      });
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleInterestChange = (interestId) => {
    setFormData((prevState) => {
      if (prevState.interests.includes(interestId)) {
        return {
          ...prevState,
          interests: prevState.interests.filter((i) => i !== interestId),
        };
      } else {
        return {
          ...prevState,
          interests: [...prevState.interests, interestId],
        };
      }
    });
  };

  const handleBioChange = (e) => {
    const newBio = e.target.value;
    if (newBio.length <= 100) {
      setFormData({ ...formData, bio: newBio });
      setCharCount(newBio.length);
    }
  };

  const handleCountryChange = (e) => {
    setSelectedCountry(e.target.value);
    setFormData({ ...formData, countryId: e.target.value });
  };

  const autoResize = (e) => {
    e.target.style.height = 'auto';
    e.target.style.height = `${e.target.scrollHeight}px`;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Check if the bio is empty
    if (!formData.bio.trim()) {
      setErrorMessage("Bio is required."); // Display an error message if bio is empty
      return;
    }

    if (!selectedCountry) {
      alert("Please select a country");
      return;
    }

    setPasswordError("");
    setErrorMessage("");

    if (formData.password !== formData.confirmPassword) {
      setPasswordError("Confirm Password must be the same as Password");
      return;
    }

    if (formData.interests.length === 0) {
      setErrorMessage("Please select at least one interest.");
      return;
    }

    const registrationData = {
      fullName: formData.fullName,
      email: formData.email,
      password: formData.password,
      interests: formData.interests,
      countryId: formData.countryId,
      bio: formData.bio,
    };

    axios
      .post("http://localhost:8080/api/users/register", registrationData)
      .then((response) => {
        alert("User registered successfully!");
        window.location.href = "/login";
      })
      .catch((error) => {
        if (error.response && error.response.status === 400) {
          const errorMsg = error.response.data;
          if (errorMsg === "Email already exists") {
            setErrorMessage("Email already exists");
          } else if (errorMsg === "Full Name already exists") {
            setErrorMessage("Full Name already taken");
          } else {
            setErrorMessage("Registration failed. Please try again.");
          }
        } else {
          setErrorMessage("Registration failed. Please try again.");
        }
        console.error("Error registering user:", error);
      });
  };

  return (
    <div className="register-page">
      <div className="register-form">
        <h1>Eco2M – Register</h1>
        <div className="register-container">
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <input
                type="text"
                name="fullName"
                placeholder="🤩Full Name🤩"
                value={formData.fullName}
                onChange={handleChange}
                className="input-field"
                required
              />
            </div>
            <div className="form-group">
              <input
                type="email"
                name="email"
                placeholder="📧Email - Id📧"
                value={formData.email}
                onChange={handleChange}
                className="input-field"
                required
              />
            </div>
            <div className="form-group">
              <input
                type="password"
                name="password"
                placeholder="💀Password💀"
                value={formData.password}
                onChange={handleChange}
                className="input-field"
                required
              />
            </div>
            <div className="form-group">
              <input
                type="password"
                name="confirmPassword"
                placeholder="☠️Confirm Password☠️"
                value={formData.confirmPassword}
                onChange={handleChange}
                className="input-field"
                required
              />
              {passwordError && <p className="error-message">{passwordError}</p>}
            </div>

            {/* Bio Section */}
            <div className="form-group bio-container">
              <textarea
                id="bio"
                name="bio"
                placeholder="😇Biography: ❤️Tell us about yourself✨..."
                value={formData.bio}
                onChange={handleBioChange}
                onInput={autoResize}
                maxLength="100"
                className="bio-textarea"
                rows={1}
              ></textarea>
              <span className={`char-counter ${charCount === 100 ? 'limit-reached' : ''}`}>
                {charCount}/100
              </span>
            </div>

            {/* Country Selection */}
            <div className="form-group country-select">
              <label htmlFor="country" className="country-label">
                Select your country:
              </label>
              <select
                id="country"
                value={formData.countryId}
                onChange={handleCountryChange}
                className="country-dropdown"
              >
                <option value="">
                  -- Please -- Select -- Your -- Country --
                </option>
                {countries.map((country) => (
                  <option key={country.countryId} value={country.countryId}>
                    {country.countryName}
                  </option>
                ))}
              </select>
            </div>

            <p className="interests-header">
              Select all the options, in which you wish to participate to save
              our ecosystem
            </p>
            <div className="interests-options">
              {interestsOptions.map((interest) => (
                <div
                  key={interest.interestId}
                  className={`interest-bubble ${
                    formData.interests.includes(interest.interestId) ? "selected" : ""
                  }`}
                  onClick={() => handleInterestChange(interest.interestId)}
                >
                  {interest.interestName}
                </div>
              ))}
            </div>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            <button type="submit" className="register-button">
              🥳Register🥳
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default RegisterPage;
