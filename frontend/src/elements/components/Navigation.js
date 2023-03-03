import {Link} from "react-router-dom";
import '../css/Navigation.css';

const Navigation = (loggedUser) => {
    return (
        <nav className="navBar">
            <div>
                <img src="/69piR5.jpg" height="50px" width="100px" alt=""/>
            </div>
            <div className='navLinks'>
                <Link to="/">Home</Link>
                <Link to="/games">Games</Link>
                <Link to="/shop">Shop</Link>
                <Link to="/news">News</Link>
            </div>
            <div>
                <Link to="/support">Support</Link>
                <Link to="/myAccount">My Account</Link>
            </div>
        </nav>
    )
};

export default Navigation;