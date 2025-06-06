import React, { useContext } from 'react';
   import { useNavigate } from 'react-router-dom';
   import { AuthContext } from '../AuthContext';
import axios from 'axios';

   const LogoutButton = () => {
       const { setIsAuthenticated } = useContext(AuthContext);
       const navigator = useNavigate();

       const handleLogout = async () => {
           try{
            await axios.post('http://localhost:8080/api/auth/logout', {}, { withCredentials: true});
            setIsAuthenticated(false);
            navigator('/login');
           } catch (error){
            console.error(error);
           }
       };

       return (
           <button className="btn btn-secondary" onClick={handleLogout}>
               Logout
           </button>
       );
   };

   export default LogoutButton;