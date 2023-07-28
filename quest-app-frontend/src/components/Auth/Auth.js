import React, { useState } from 'react';
import { Button, FormControl, Input, InputLabel, FormHelperText } from '@mui/material';
import { useNavigate, Navigate } from 'react-router';

function Auth() {

    const navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleUsername = (value) => {
        setUsername(value);
    }

    const handlePassword = (value) => {
        setPassword(value);
    }

    const handleButton = (path) => {
        console.log("Handlebutton")
        sendRequest(path)
        setUsername("")
        setPassword("")
        navigate("/")
    }

    const sendRequest = (path) => {
        fetch("/auth/" + path, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                userName: username,
                password: password,
            }),
        })
            .then((res) => res.json())
            .then((result) => { localStorage.setItem("tokenKey", result.message); localStorage.setItem("currentUser", result.userId); localStorage.setItem("userName", username); })
            .catch((err) => console.log(err))
    }

    return (
        <FormControl>
            <InputLabel style={{ top: 30 }}>Username</InputLabel>
            <Input style={{ top: 25 }} onChange={(i) => handleUsername(i.target.value)} value={username} />
            <InputLabel style={{ top: 100 }} >Password</InputLabel>
            <Input style={{ top: 50 }} onChange={(i) => handlePassword(i.target.value)} value={password} />

            <Button
                variant='contained'
                style={{ marginTop: 80, background: 'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)', color: 'white' }}
                onClick={() => handleButton("register")}
            >Register</Button>
            <FormHelperText style={{ marginTop: 50 }} >Are you already registered?</FormHelperText>
            <Button
                variant='contained'
                style={{ marginTop: 20, background: 'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)', color: 'white' }}
                onClick={() => handleButton("login")}
            >Login</Button>
        </FormControl>
    );

}

export default Auth;