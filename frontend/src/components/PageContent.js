import React, { useContext } from "react";
import UserContext from "../context/UserContext";
import LoginPage from "./LoginPage";
import PostForm from "./PostForm";
import Feed from "./Feed";

export default function PageContent({ state, dispatch }) {
  const providerValue = useContext(UserContext);
  const userInfo = providerValue.userInfo;

  return (
    <div>
      {userInfo.role === "USER" ? (
        <div>
            <PostForm state={state} dispatch={dispatch}></PostForm>
            <Feed state={state} dispatch={dispatch}></Feed>
        </div>
      ) : (
        <LoginPage></LoginPage>
      )}
    </div>
  );
}
