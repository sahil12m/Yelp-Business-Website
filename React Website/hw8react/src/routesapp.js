import React from 'react'
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import Bookings from './Components/Bookings'
import Navbar from './Components/Navbar'
import Form from './Components/Form'


export default function Routesapp() {
  return (
    <BrowserRouter>
    <Navbar/>
    <Routes>
        <Route path='/' element={<Navigate to="/search" replace/>}/>
        <Route path='/search' element={<Form/>}/>
        <Route path='/bookings' element={<Bookings/>}/>
    </Routes>
    </BrowserRouter>
  )
}
