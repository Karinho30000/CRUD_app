import React from 'react';
import { useNavigate } from 'react-router-dom';
import LogoutButton from './LogoutButton';

const HeaderComponent = () => {
  const navigator = useNavigate();

  return (
    <header>
      <nav className="navbar navbar-dark px-3" style={{ backgroundColor: 'deepskyblue' }}>
        <div className="container-fluid d-flex justify-content-between align-items-center w-100">
          <button
            className="btn btn-outline-light"
            onClick={() => navigator('/teachers')}
          >
            All Teachers
          </button>

          <div className="d-flex align-items-center">
            <LogoutButton />
            <img
              src="/hd_logo_2.png"
              alt="Logo"
              style={{ height: '50px', marginLeft: '30px' }}
            />
          </div>
        </div>
      </nav>
    </header>
  );
};

export default HeaderComponent;
