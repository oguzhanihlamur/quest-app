import { Avatar, CardContent, InputAdornment, OutlinedInput } from '@mui/material';
import React from 'react';
import { Link } from 'react-router-dom';

function Comment(props) {

    const { userId, text, userName } = props;

    return (
        <CardContent style={{}}>

            <OutlinedInput
                disabled
                id='outlined-adorment-amount'
                multiline
                placeholder='Title'
                inputProps={{ maxLength: 25 }}
                fullWidth
                value={text}
                startAdornment={
                    <InputAdornment position='start'>
                        <Link style={{ textDecoration: "none", boxShadow: "none", color: "white" }} to={{ pathname: '/users/' + userId }}>
                            <Avatar sx={{ background: 'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)' }} aria-label="recipe">
                                {userName.charAt(0).toUpperCase()}
                            </Avatar>
                        </Link>
                    </InputAdornment>
                }
                style={{ color: "black", background: "white" }}
            ></OutlinedInput>
        </CardContent>
    );

}

export default Comment;