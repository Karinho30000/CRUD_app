import React, { useEffect, useState } from 'react'
import { createStudent, getStudent, updateStudent } from '../services/StudentService'
import { useNavigate, useParams, useLocation } from 'react-router-dom';
import NavigationButtons from './NavigationButtons';

const StudentComponent = () => {

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [group, setGroup] = useState('')
    const [location, setLocation] = useState('')
    const [teacher, setTeacher] = useState('')
    const [availableGroups, setAvailableGroups] = useState([])
    const [availableLocations, setAvailableLocations] = useState([])
    const [availableTeachers, setAvailableTeachers] = useState([])

    const {id} = useParams();

    const [errors, setErrors] = useState({
      firstName: '',
      lastName: '',
      group: '',
      teacher: '',
      location: ''
    })
    
    const navigator = useNavigate();
    const loc = useLocation();
    const preselectedGroup = loc.state?.preselectedGroup;
    const preselectedLocation = loc.state?.preselectedLocation;

    useEffect(() => {
      if (preselectedGroup && !id) {
        setGroup(preselectedGroup);
      }

      if (preselectedLocation && !id) {
        setLocation(preselectedLocation);
      }
    }, [preselectedGroup, preselectedLocation, id]);


    useEffect(() => {
        if(id){
          getStudent(id).then((response) => {
            setFirstName(response.data.firstName);
            setLastName(response.data.lastName);
            setGroup(response.data.group);
            setTeacher(response.data.teacher);
            setLocation(response.data.location);
          }).catch(error =>  {
            console.error(error);
          })
        }
    }, [id])

    useEffect(() => {
      if(group){
        fetch(`${import.meta.env.VITE_API_BASE_URL}/api/teachers/group/${group}`)
        .then((response) => response.json())
        .then((data) => setAvailableTeachers(data))
        .catch(error => console.error(error));
      }
    })

    useEffect(() => {
      if(location){
        fetch(`${import.meta.env.VITE_API_BASE_URL}/api/locations/${location}/groups`)
        .then((response) => response.json())
        .then((data) => setAvailableGroups(data))
        .catch(error => console.error(error));
      }
    }, [location])

    useEffect(() => {
      fetch(`${import.meta.env.VITE_API_BASE_URL}/api/locations`)
      .then((response) => response.json())
      .then((data) => setAvailableLocations(data))
      .catch(error => console.error(error));
    }, [])

    function saveOrUpdateStudent(s){
      s.preventDefault();

      if(validateForm()){

        const student = {firstName, lastName, group, location, teacher}
        console.log(student)

        if(id){
          updateStudent(id, student).then((response) => {
            console.log(response.data);
            navigator(`/locations/${location}/groups/${group}/students`);
          }).catch(error => {
            console.error(error);
          })
        } else{
            createStudent(student).then((response) => {
              console.log(response.data);
              navigator(`/locations/${location}/groups/${group}/students`);
            }).catch(error => {
                console.error(error);
            })
        }
      }
    }

    function validateForm(){
      let valid = true;

      const errorsCopy = {... errors}

      if(firstName.trim()){
        errorsCopy.firstName = '';
      } else{
        errorsCopy.firstName = 'First name is required';
        valid = false;
      }

      if(lastName.trim()){
        errorsCopy.lastName = '';
      } else{
        errorsCopy.lastName = 'Last name is required';
        valid = false;
      }

      if(group.trim()){
        errorsCopy.group = '';
      } else{
        errorsCopy.group = 'Group is required';
        valid = false;
      }

      if(teacher.trim()){
        errorsCopy.teacher = '';
      } else{
        errorsCopy.teacher = 'Teacher is required';
        valid = false;
      }

      if(location.trim()){
        errorsCopy.location = '';
      } else{
        errorsCopy.location = 'Location is required';
        valid = false;
      }

      setErrors(errorsCopy);

      return valid;
    }

    function pageTitle(){
      if(id){
          return <h2 className='text-center'>Update Student</h2>
      } else{
          return <h2 className='text-center'>Add Student</h2>
      }
    }

  return (
    <div className='container'>
      <NavigationButtons/>
      <div className='row'>
        <div className='card col-md-6 offset-md-3'>
          {
            pageTitle()
          }
          <div className='card-body'>
            <form>
              <div className='form-group mb-2'>
                <label className='form-label'>First Name:</label>
                <input
                  type='text'
                  placeholder='Enter Students First Name'
                  name='firstName'
                  value={firstName}
                  className={`form-control ${ errors.firstName ? 'is-invalid': '' }`}
                  onChange={(s) => setFirstName(s.target.value)}
                >
                </input>
                { errors.firstName && <div className='invalid-feedback'> {errors.firstName} </div> }
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Last Name:</label>
                <input
                  type='text'
                  placeholder='Enter Students Last Name'
                  name='lastName'
                  value={lastName}
                  className={`form-control ${ errors.lastName ? 'is-invalid': '' }`}
                  onChange={(s) => setLastName(s.target.value)}
                >
                </input>
                { errors.lastName && <div className='invalid-feedback'> {errors.lastName} </div> }
              </div>

              <div className='form-group mb-2 w-75'>
                <label className='form-label'>Location:</label>
                
                  <select
                    value={location}
                    onChange={(s) => setLocation(s.target.value)}
                    className={`form-control ${errors.location ? 'is-invalid' : ''} ${location === '' ? 'placeholder-center' : ''}`}
                    disabled={!id && loc.state?.preselectedLocation}>

                      <option>-- Select Location --</option>
                      {availableLocations.map((l) => (
                        <option key={l.name} value={l.name}>{l.name}</option>
                      ))}
                    </select>

                { errors.location && <div className='invalid-feedback'> {errors.location} </div> }
              </div>

              <div className='form-group mb-2 w-75'>
                <label className='form-label'>Group:</label>
                  <select
                    value={group}
                    onChange={(s) => setGroup(s.target.value)}
                    className={`form-control ${ errors.group ? 'is-invalid': ''} ${group === '' ? 'placeholder-center' : ''}`}
                    disabled={!id && loc.state?.preselectedGroup}>
                    

                      <option>-- Select Group --</option>
                      {availableGroups.map((g) => (
                        <option key={g.name} value={g.name}>{g.name}</option>
                      ))}
                  </select>

                { errors.group && <div className='invalid-feedback'> {errors.group} </div> }
              </div>

              <div className='form-group mb-2 w-75'>
                <label className='form-label'>Teacher:</label>
                  <select
                    value={teacher}
                    onChange={(s) => setTeacher(s.target.value)}
                    className={`form-control ${ errors.teacher ? 'is-invalid': ''} ${teacher === '' ? 'placeholder-center' : ''}`}>
                    

                      <option>-- Select Teacher --</option>
                      {availableTeachers.map((t) => (
                        <option key={t.name} value={t.name}>{t.name}</option>
                      ))}
                  </select>

                { errors.group && <div className='invalid-feedback'> {errors.group} </div> }
              </div>


              <button className='btn btn-success' onClick={saveOrUpdateStudent}>Submit</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default StudentComponent
