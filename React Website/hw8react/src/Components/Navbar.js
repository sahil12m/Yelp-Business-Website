import {Link} from 'react-router-dom';
import React from 'react';
import './Navbar.css';

export default function Navbar() {
  return (
    <div className='navbar-holder'>
      <ul className="nav justify-content-end">
        <li className="nav-item">
          <Link className="nav-link testsearch search active" to="/search">Search</Link>
        </li>
        <li className="nav-item bookings">
          <Link className="nav-link testbook bookings active" to="/bookings">My Bookings</Link>
        </li>
      </ul>
    </div>
  )
}
