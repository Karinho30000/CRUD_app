import React from 'react'
import { useNavigate } from 'react-router-dom'

const HeaderComponent = () => {

  const navigator = useNavigate();

  return (
    <div>
        
    	<header>
            <nav className='navbar navbar-dark px-3' 
                 style={{backgroundColor: 'deepskyblue'}}>
              <button
                className='btn btn-outline-light'
                onClick={() => navigator('/teachers')}
              >
                All Teachers
              </button>
              <img
                src="/hd_logo.png"
                alt="Logo"
                className="border border-dark-subtle"
                style={{ height: '40px', }}
              />
            </nav>

        </header>

    </div>
  )
}

export default HeaderComponent