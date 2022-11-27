import React, {useState} from "react";
import {useSearchParams} from "react-router-dom";
import { env } from "../env";

type LoginProps = {
}

export const LoginComponent: React.FunctionComponent<LoginProps> = (props: LoginProps) => {

    let [username, setUsername] = useState('');
    let [password, setPassword] = useState('');
    let [searchParams, ] = useSearchParams();
    const redirectUrl: string | null = searchParams.get('redirectUrl');
    const roles: string | null = searchParams.get('roles');
    const clientId: string | null = searchParams.get('clientId');

    function onUsernameUpdate(e: React.ChangeEvent<HTMLInputElement>){
        setUsername(e.target.value)
        console.log(username)
    }

    function onPasswordUpdate(e: React.ChangeEvent<HTMLInputElement>){
        setPassword(e.target.value)
        console.log(password)
    }

    async function onLoginPressed(e: React.MouseEvent<HTMLButtonElement, MouseEvent>){

        const _body = {
            redirectUrl: redirectUrl,
            roles: roles?.split(",")
        }

        const _headers = new Headers();
        _headers.set('Content-Type', 'application/json');
        _headers.set('X-CLIENT-ID', clientId == null ? '' : clientId);
        _headers.set('X-USER-NAME', username);
        _headers.set('X-AUTHORIZATION', btoa(password));


        const response: Response = await fetch("http://localhost:8080/oauth/access-token", {
            method: "POST",
            body: JSON.stringify(_body),
            headers: _headers,
            redirect: "follow"
        })

        console.log(response.json());
    }

    return (
            <div>
                <h2>{redirectUrl}</h2>
                <h2>{roles}</h2>
                <h2>{clientId}</h2>
                <h1>Username</h1>
                <input type="text" name="txtUsername" id="txtUsername" onChange={(e) => onUsernameUpdate(e)}/>
                <h1>Password</h1>
                <input type="password" name="txtPassword" id="txtPasssword" onChange={(e) => onPasswordUpdate(e)}/>
                <button name="btnSubmit" id="btnSubmit" onClick={(e) => onLoginPressed(e)} >Submit</button>
            </div>
            )

}