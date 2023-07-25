import { Avatar, Button, CardContent, InputAdornment, OutlinedInput } from '@mui/material';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';

function CommentForm(props) {

    const { userId, userName, postId } = props;

    const [comment, setComment] = useState("");

    const saveComment = () => {
        fetch("/comments", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                postId: postId,
                userId: userId,
                text: comment,
            }),
        })
            .then((res) => res.json())
            .catch((err) => console.log(err))
    }

    const handleSubmit = () => {
        saveComment();
        setComment('');
    }

    const handleChange = (value) => {
        setComment(value);
    }
    return (
        <CardContent style={{}}>

            <OutlinedInput
                id='outlined-adorment-amount'
                multiline
                placeholder='Enter your comment.'
                inputProps={{ maxLength: 250 }}
                fullWidth
                onChange={i => handleChange(i.target.value)}
                value={comment}
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
                endAdornment={
                    <InputAdornment position='end'>
                        <Button
                            variant='contained'
                            style={{ background: 'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)', color: 'white' }}
                            onClick={handleSubmit}
                        >
                            Comment
                        </Button>
                    </InputAdornment>}>

            </OutlinedInput>
        </CardContent>
    );

}

export default CommentForm;