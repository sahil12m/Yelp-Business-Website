import React from 'react'
import { useLocalStorage } from "../useLocalStorage";
import { useState, useEffect } from 'react';

// AIzaSyBLZmOejZR198CAWqDNlk87ZLcSxfRL8Ys
export default function Bookings() {

  const [femail, setfemail] = useLocalStorage('');
  const [fdate, setfdate] = useLocalStorage('');
  const [ftime1, setftime1] = useLocalStorage('');
  const [ftime2, setftime2] = useLocalStorage('');
  const [busname, setbusname] = useLocalStorage('');

  useEffect(() => {
    let items = ''
    if(localStorage.getItem('date') !== 'undefined' && localStorage.getItem('date') !== 'null'){
      items = JSON.parse(localStorage.getItem('date'));
    }if (items) {
    setfdate(items);
    }
  }, []);

  useEffect(() => {
    let items = ''
    if(localStorage.getItem('email') !== 'undefined' && localStorage.getItem('email') !== 'null'){
     items = JSON.parse(localStorage.getItem('email'));
    }if (items) {
      setfemail(items);
    }
  }, []);

  useEffect(() => {
    let items = ''
    if(localStorage.getItem('name') !== 'undefined' && localStorage.getItem('name') !== 'null'){
     items = JSON.parse(localStorage.getItem('name'));
    }
    if (items) {
      setbusname(items);
    }
  }, []);

  useEffect(() => {
    let items = ''
    if(localStorage.getItem('time1') !== 'undefined' && localStorage.getItem('time1') !== 'null'){
     items = JSON.parse(localStorage.getItem('time1'));
    }if (items) {
      setftime1(items);
    }
  }, []);

  useEffect(() => {
    let items = ''
    if(localStorage.getItem('time2') !== 'undefined' && localStorage.getItem('time2') !== 'null'){
      items = JSON.parse(localStorage.getItem('time2'));
    }if (items) {
      setftime2(items);
    }
  }, []);



  let counter = 0;

  return (
    <div className='container text-center bg-white'>
      <h1>List of your reservations</h1>
      <table className="table tab">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Business Name</th>
            <th scope="col">Date</th>
            <th scope="col">Time</th>
            <th scope="col">Email</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{counter+=1}</td>
            <td>{busname}</td>
            <td>{fdate}</td>
            <td>{ftime1}:{ftime2}</td>
            <td>{femail}</td>
          </tr>
        </tbody>
      </table>
    </div>
      
  )
}
