import React, { useContext } from "react";
import UserContext from "../context/UserContext";
import "../styles.css";

export default function Header() {
  const providerValue = useContext(UserContext);
  const userInfo = providerValue.userInfo;

  return (
    <div className="header">
      <div className="title">
        <img src="icon.png" alt="Chitter" width="150px" height="150px"></img>
        <h2>chitter</h2>
      </div>
      {
        userInfo.username?
        <div className="header-username">
            <p>Welcome, {userInfo.username}</p>
        </div> :
        <></>
      }
      
    </div>
  );
}
