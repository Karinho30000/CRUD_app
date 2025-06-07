import React, {useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom'
import { listLocations } from '../services/StudentService'

const LocationChoice = () => {
  const [locations, setLocations] = useState([]);
  const [error, setError] = useState('');
  const navigator = useNavigate();

  useEffect(() => {
    console.log("LocationChoice: Fetching locations...");
    getAllLocations();
  })

  function getAllLocations(){
    listLocations().then((response) => {
      console.log("Locations fetched: ", response.data);
      setLocations(response.data);
    }).catch(error => {
        console.error(error);
        if(error.response && error.response.status === 401){
          console.log("Unauth, to login...");
          navigator('/login');
        } else{
          setError('Failed to load locations');
        }
    });
  }

  return (
    <div className='container mt-4'>
      <h2 className='text-center mb-4'>Locations</h2>
      <div className='text-center mb-4'>
      <button
        className='btn-deepsky'
        onClick={() => navigator('/students')}
      >
        View All Students
      </button>
      </div>
      <div className='row'>
        {locations.map(location => (
          <div className='col-md-4 mb-3' key={location.name}>
            <div className='card h-100 shadow'>
              <div className='card-body'>
                <h5 
                  className='card-title text-hover'
                  style={{cursor: 'pointer', color: 'deepskyblue'}}
                  onClick={() => navigator(`/locations/${location.name}/groups`)}
                >{location.name}</h5>
                <p className='card-text'><strong>Students:</strong> {location.studentCount} </p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default LocationChoice
