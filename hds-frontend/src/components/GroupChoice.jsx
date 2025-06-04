import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { listGroups } from '../services/StudentService';
import NavigationButtons from './NavigationButtons';

const GroupChoice = () => {
  const [groups, setGroups] = useState([]);
  const [students, setStudents] = useState([]);
  const {name} = useParams();
  const navigator = useNavigate();

  useEffect(() => {
    fetch("http://localhost:8080/api/students")
    .then((res) => res.json())
    .then(setStudents);
  }, []);

  useEffect(() => {
    if(name){
      getGroupsByLocation(name);
    }else{
      getAllGroups();
    }
  }, [name])

  function getAllGroups(){
    listGroups().then((response) => {
      setGroups(response.data);
    }).catch(error => {
        console.error(error);
    })
  }

  function getGroupsByLocation(locationName){
    fetch(`http://localhost:8080/api/locations/${locationName}/groups`)
    .then((response) => response.json())
    .then((data) => {
        setGroups(data);
    }).catch(error => {
        console.error(error);
    })
  }


  return (
    <div className='container mt-4'>
      <NavigationButtons/>
      <h2 className='text-center mb-4'>Groups - {name}</h2>
      <div className='text-center mb-4'>
      <button
        className='btn-deepsky'
        onClick={() => navigator(`/locations/${name}/students`)}
      >
        View All Students - {name}
      </button>
      </div>
      <div className='row'>
        {groups.map(group => (
          <div className='col-md-4 mb-3' key={group.name}>
            <div className='card h-100 shadow'>
              <div className='card-body'>
                <h5 
                  className='card-title text-hover'
                  style={{cursor: 'pointer', color: 'deepskyblue'}}
                  onClick={() => navigator(`/locations/${group.location}/groups/${group.name}/students`)}
                >{group.name}</h5>
                <p className='card-text'>
                  <strong>Teachers:</strong>{' '}
                  {group.teachers && group.teachers.length > 0 ? (
                    group.teachers.map((teacher, index) => {
                      const count = students.filter(
                        (student) => 
                          student.group === group.name && student.location === group.location && student.teacher === teacher
                      ).length;
                      return (
                      <span key={index}>
                        {teacher} ({count})
                        {index < group.teachers.length - 1 && ', '}
                      </span>
                    )
                  })
                  ) : (
                    <em>No teachers assigned</em>
                  )}
                </p>
                <p className='card-text'><strong>Students:</strong> {group.studentCount} </p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default GroupChoice