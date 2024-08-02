import React, { useState, useMemo } from "react";
import UserContext from "./UserContext";

export const UserProvider = ({children}) => {

    const initialUserInfo = {
        "username": null,
        "role": null,
        "token": null
    };

    const [userInfo, setUserInfo] = useState(initialUserInfo);
    const providerValue = useMemo(() => ({
        userInfo, setUserInfo
    }), [userInfo]);

    return (
        <UserContext.Provider value={providerValue}>
            {children}
        </UserContext.Provider>
    );
}