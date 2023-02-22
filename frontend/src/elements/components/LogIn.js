import {useState} from "react";

const LogIn = () => {
    const [username, setUsername] = useState("");
    const [pwd, setPwd] = useState("");
    const onChangeUsername = event => {
        setUsername(event.target.value);
    }
    const onChangePwd = event => {
        setPwd(event.target.value);
    }
    const handleSubmit = (e) => {

    }
    return (
        <>
            <center><br/><br/>
            <h1>
                LOGIN
            </h1>
            <p>Fill in your personal information</p><br/>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username" className="formLabelUsername">Username</label><br/>
                <input type="text" name="username" onChange={onChangeUsername} className="Label"/><br/><br/>
                <label htmlFor="password" className="formLabelPassword">Password</label><br/>
                <input type="password" name="password" onChange={onChangePwd} className="Label"/>
                <br/><br/>
                <button type="submit" className="normalButton">Submit</button><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
            </form>
            </center>
        </>
    )
}
export default LogIn;