import React, { useState, useContext } from 'react';
   import axios from 'axios';
   import { useNavigate } from 'react-router-dom';
   import { AuthContext } from '../AuthContext';
   import 'bootstrap/dist/css/bootstrap.min.css';
   import { FaEye, FaEyeSlash } from 'react-icons/fa';

   const Login = () => {
       const [username, setUsername] = useState('');
       const [password, setPassword] = useState('');
       const [error, setError] = useState('');
       const [showPassword, setShowPassword] = useState(false);
       const { setIsAuthenticated } = useContext(AuthContext);
       const navigator = useNavigate();

       const handleSubmit = async (e) => {
           e.preventDefault();
           try {
               const response = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/api/auth/login`, { username, password }, { withCredentials: true });
               if(response.status === 200){
                setIsAuthenticated(true);
                navigator('/locations');
               }
           } catch (err) {
               setError('Invalid username or password');
           }
       };

      const togglePassword = () => {
        setShowPassword(prev => !prev);
      };

       return (
        <div className="container mt-5 d-flex justify-content-center">
            <div className="login-form" style={{ width: '100%', maxWidth: '400px' }}>
            <h2 className="text-center">Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                <label className="form-label">Username</label>
                <input
                    type="text"
                    className="form-control"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
                </div>
                <div className="mb-3 position-relative">
                <label className="form-label">Password</label>
                <input
                    type={showPassword ? 'text' : 'password'}
                    className="form-control"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <span
                            onClick={togglePassword}
                            style={{
                                position: 'absolute',
                                right: '10px',
                                top: '50%',
                                cursor: 'pointer',
                                color: '#666'
                            }}
                        >
                            {showPassword ? <FaEyeSlash /> : <FaEye />}
                        </span>
                </div>
                <button type="submit" className="btn btn-primary w-100">Login</button>
                {error && <p className="text-danger mt-3 text-center">{error}</p>}
            </form>
            </div>
        </div>
        );


   };

   export default Login;
