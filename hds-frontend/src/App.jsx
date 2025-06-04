import './App.css'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListStudentComponent from './components/ListStudentComponent'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import StudentComponent from './components/StudentComponent'
import GroupChoice from './components/GroupChoice'
import LocationChoice from './components/LocationChoice'
import TeacherList from './components/TeacherList'

function App() {

  return (
    <div className="app-wrapper">
      <BrowserRouter>
          <HeaderComponent/>
          <div className='main-content'>
            <Routes>
              {/* // http://localhost:3000 */}
                <Route path='/' element = {<LocationChoice/>}></Route>
                {/* // http://localhost:3000/students */}
                <Route path='/students' element = {<ListStudentComponent/>}></Route>
                {/* // http://localhost:3000/teachers */}
                <Route path='/teachers' element = {<TeacherList/>}></Route>
                {/* // http://localhost:3000/add-student */}
                <Route path='/add-student' element = { <StudentComponent/>}></Route>
                {/* // http://localhost:3000/edit-student/1 */}
                <Route path='/edit-student/:id' element = { <StudentComponent/> }></Route>
                {/* // http://localhost:3000/locations */}
                <Route path='/locations' element= {<LocationChoice/>}></Route>
                {/* // http://localhost:3000/locations/name_of_location/groups */}
                <Route path='/locations/:name/groups' element= {<GroupChoice/>}></Route>
                {/* // http://localhost:3000/locations/name_of_location/students */}
                <Route path='/locations/:name_location/students' element= {<ListStudentComponent/>}></Route>
                {/* // http://localhost:3000/locations/name_of_location/groups/name_of_group/students */}
                <Route path='/locations/:name_location/groups/:name_group/students' element= {<ListStudentComponent/>}></Route>
            </Routes>
          </div>  
          <FooterComponent/>
      </BrowserRouter>    
    </div>
  )
}

export default App
