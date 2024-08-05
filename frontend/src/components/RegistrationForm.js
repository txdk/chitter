import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../service/AuthenticationService";

export default function RegistrationForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirmation, setPasswordConfirmation] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();

  const clearForm = () => {
    setUsername("");
    setPassword("");
    setPasswordConfirmation("");
  }

  const handleSubmit = (e) => {
    e.preventDefault();

    if (password !== passwordConfirmation) {
      setErrorMessage("Passwords must match.");
      return;
    }

    const registrationData = {
      username: username,
      password: password,
    };

    register(registrationData)
      .then((response) => {
        console.log(response);
        setErrorMessage("");
        clearForm();
        alert("Registration successful!");
        navigate("/");
      })
      .catch((error) => {
        setErrorMessage("Registration failed.");
        console.error(error);
      });
  }

  return (
    <form className="login-page" onSubmit={handleSubmit}>
      <div className="container">
        <h2>📝 Create an account</h2>
        <button className="button header-button" onClick={() => {navigate("/")}}>
          ⮐
        </button>
      </div>
      <div>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        ></input>
      </div>
      <div>
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        ></input>
      </div>
      <div>
        <input
          type="password"
          placeholder="Confirm password"
          value={passwordConfirmation}
          onChange={(e) => setPasswordConfirmation(e.target.value)}
        ></input>
      </div>
      <div>
        <button className="button" type="submit">
            Register
        </button>
      </div>
      {
        errorMessage.length > 0?
        <p className="failure-text">{errorMessage}</p> :
        <></>
      }
    </form>
  );
}
