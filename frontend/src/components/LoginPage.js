import React, { useContext, useState } from "react";
import { Link } from "react-router-dom";
import { login } from "../service/AuthenticationService";
import UserContext from "../context/UserContext";

export default function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isFailedAttempt, setIsFailedAttempt] = useState(false);

  const providerValue = useContext(UserContext);
  const setUserInfo = providerValue.setUserInfo;

  const clearForm = () => {
    setUsername("");
    setPassword("");
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const loginData = {
      username: username,
      password: password,
    };

    login(loginData)
      .then((response) => {
        const user = {
          username: response.data.username,
          role: response.data.role,
          token: response.data.token,
        };
        setUserInfo(user);
        setIsFailedAttempt(false);
      })
      .catch((error) => {
        setIsFailedAttempt(true);
        console.error(error);
      });

    clearForm();
  };

  return (
    <form className="login-page" onSubmit={handleSubmit}>
      <h2>🔐 Login</h2>
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
        <button className="button" type="submit">
          Login
        </button>
      </div>
      {isFailedAttempt ? (
        <div className="failure-text">
          <p>Login attempt failed.</p>
        </div>
      ) : (
        <></>
      )}
      <p>
        Don't have an account?{" "}
        <Link to="/register">Register now!</Link>
      </p>
    </form>
  );
}
