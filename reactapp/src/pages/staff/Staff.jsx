import React, { useEffect, useState } from 'react'
import '../styles.css';
import axios from 'axios';
import Swal from 'sweetalert2';
import DashboardHeader from '../../components/DashboardHeader';
const Staff = () => {
    const [staff, setStaff] = useState([]);
    const [edit, setEdit] = useState(false);
    const [add, setAdd] = useState(false);
    const [staffId, setstaffId] = useState('')
    const [name, setName] = useState('')
    const [age, setAge] = useState('')
    const [gender, setGender] = useState('')
    const [address, setAddress] = useState('')
    const [phone, setPhone] = useState('')
    const [email, setEmail] = useState('')
    const [jobTitle, setJobTitle] = useState('')
    const [salary, setSalary] = useState('');
    const [searchQuery, setSearchQuery] = useState('');
    const fetchstaffs = async () => {
        await axios.get('https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/staff').then((response) => {
            setStaff(response.data);
        })
    }
    useEffect(() => {
        fetchstaffs();
    }, [])
    const SubmitDelete = async (id) => {
        await axios.delete(`https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/staff/${id}`).then(() => {
            fetchstaffs();
        })
    }
    const handleDelete = (id) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showDenyButton: true,
            confirmButtonText: 'Yes',
            denyButtonText: `No`,
        }).then((result) => {
            if (result.isConfirmed) {
                SubmitDelete(id);
                Swal.fire({
                    icon: 'success',
                    text: "Success",
                    title: 'Staff Deleted',
                    showConfirmButton: false,
                    timer: 1000
                })
            }
        })
    }
    const handleEdit = (staff) => {
        setEdit(true);
        setName(staff.name)
        setAddress(staff.address)
        setAge(staff.age)
        setGender(staff.gender)
        setstaffId(staff.id)
        setEmail(staff.email)
        setPhone(staff.phone)
        setJobTitle(staff.jobTitle);
        setSalary(staff.salary)

    }
    const handleNewStaff = (e) => {
        e.preventDefault();
        setAdd(true);
        setEdit(false);
        clearFields();
    }
    const handleBack = (e) => {
        e.preventDefault();
        setAdd(false);
        setEdit(false);
        clearFields();
    }
    const handleView = (staff) => {
        Swal.fire({
            title: 'Staff Details',
            html: `
            <p><strong>Name:</strong> ${staff.name}</p>
            <p><strong>Age:</strong> ${staff.age}</p>
            <p><strong>Gender:</strong> ${staff.gender}</p>
            <p><strong>Address:</strong> ${staff.address}</p>
            <p><strong>Phone:</strong> ${staff.phone}</p>
            <p><strong>Email:</strong> ${staff.email}</p>
            <p><strong>Job Title:</strong> ${staff.jobTitle}</p>
            <p><strong>Salary:</strong> ${staff.salary}</p>
          `,
            confirmButtonText: 'Close',
            showConfirmButton: true,
        });
    };
    const handleEditSubmit = async (e) => {
        e.preventDefault();
        const updatedstaff = {
            id: staffId,
            name: name,
            age: age,
            gender: gender,
            address: address,
            phone: phone,
            email: email,
            jobTitle: jobTitle,
            salary: salary
        }
        await axios.put(`https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/staff`, updatedstaff).then(() => {
            Swal.fire({
                icon: 'success',
                title: 'staff Updated Successfully',
                showConfirmButton: false,
                timer: 1000
            })
            setEdit(false);
            clearFields();
            fetchstaffs();
        }).catch((err) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Something went wrong.Please check the details',
            })
            console.log(err);
        })
    }
    const handleAddSubmit = async (e) => {
        e.preventDefault();
        const staff = {
            name: name,
            age: age,
            gender: gender,
            address: address,
            phone: phone,
            email: email,
            jobTitle: jobTitle,
            salary: salary
        }
        await axios.post('https://8080-ddeaddfaafedbeeafbdefaebabceebadffeaeaadbdbabf.project.examly.io/staff', staff).then((response) => {
            Swal.fire({
                icon: 'success',
                title: 'staff Added',
                showConfirmButton: false,
                timer: 1000
            })
            fetchstaffs();
            setAdd(false);
            clearFields();
        }).catch((err)=>{
            
            console.log(err);
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Something went wrong.Please check the details',
            })
        })
    }
    const clearFields = () => {
        setName('')
        setAddress('')
        setAge('')
        setGender('')
        setstaffId('')
        setEmail('')
        setPhone('')
        setJobTitle('')
        setSalary();
    }
    return (
        <div>
            <div className='dashboard-content'>
                {!edit && !add && <DashboardHeader btnText="New Staff" onClick={handleNewStaff} />}
                {(edit || add) && <DashboardHeader btnText="Back to Staff" onClick={handleBack} />}
                {!edit && !add && <div className='dashboard-content-container'>
                    <div className='dashboard-content-header'>
                        <h2>Staff List</h2>
                        <div className='dashboard-content-search'>
                            <input
                                type='text'
                                placeholder='Search..'
                                className='dashboard-content-input'
                                value={searchQuery}
                                onChange={(e) => setSearchQuery(e.target.value)}
                                min="1"
                                inputMode="numeric"
                            />
                        </div>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>NAME</th>
                                <th>AGE</th>
                                <th>GENDER</th>
                                {/* <th>ADDRESS</th> */}
                                <th>PHONE</th>
                                {/* <th>EMAIL</th> */}
                                <th>JOB TITLE</th>
                                {/* <th>SALARY</th> */}
                                <th>Actions</th>
                            </tr>
                        </thead>
                        {staff.length !== 0 &&
                            <tbody>
                                {staff
                                    .filter(staff => {
                                        return (
                                            staff.id.toString().includes(searchQuery) || staff.name.toLowerCase().includes(searchQuery)
                                        );
                                    })
                                    .map((staff) => {
                                        return <tr key={staff.id}>
                                            <td><span>{staff.id}</span></td>
                                            <td><span>{staff.name}</span></td>
                                            <td><span>{staff.age}</span></td>
                                            <td><span>{staff.gender}</span></td>
                                            {/* <td><span>{staff.address}</span></td> */}
                                            <td><span>{staff.phone}</span></td>
                                            {/* <td><span>{staff.email}</span></td> */}
                                            <td><span>{staff.jobTitle}</span></td>
                                            {/* <td><span>{staff.salary}</span></td> */}
                                            {/* <td><span>{staff.treatmentPlan}</span></td> */}
                                            <td>
                                                <button onClick={() => handleEdit(staff)} className='edit-save-btn'>Edit</button>
                                                <button onClick={() => handleDelete(staff.id)} className='edit-back-btn'>Delete</button>
                                                <button className='view-btn' onClick={() => handleView(staff)}>View</button>
                                            </td>
                                        </tr>
                                    })}
                            </tbody>
                        }
                    </table>
                </div>}
                {edit &&
                    <div className='form-elements'>
                        <div className='dashboard-content-header'>
                            <h2>Edit staff Details</h2>
                        </div>
                        <div>
                            <form onSubmit={handleEditSubmit}>
                                <label htmlFor="sid">staff Id</label>
                                <br />
                                <input type="number"  id="sid" className='form-inputs' value={staffId} onChange={(e) => setstaffId(e.target.value)} readOnly />
                                <br />
                                <label htmlFor="sname" className='form_label'>Name</label>
                                <br />
                                <input type="text"  id="sname" className='form-inputs' value={name} onChange={(e) => setName(e.target.value)} required />
                                <br />
                                <label htmlFor="sage" className='form_label'>Age</label>
                                <br />
                                <input type="number"  id="sage" className='form-inputs' value={age} onChange={(e) => setAge(e.target.value)} required />
                                <br />
                                <label htmlFor="gen" className='form_label'>Gender</label>
                                <br />
                                <input type="text"  id="gen" className='form-inputs' value={gender} onChange={(e) => setGender(e.target.value)} required />
                                <br />
                                <label htmlFor="addr" className='form_label'>Address</label>
                                <br />
                                <input type="text"  id="addr" className='form-inputs' value={address} onChange={(e) => setAddress(e.target.value)} required />
                                <br />
                                <label htmlFor="ph" className='form_label'>Phone</label>
                                <br />
                                <input type="text"  id="ph" className='form-inputs' value={phone} onChange={(e) => setPhone(e.target.value)} required />
                                <br />
                                <label htmlFor="em" className='form_label'>Email</label>
                                <br />
                                <input type="text"  id="em" className='form-inputs' value={email} onChange={(e) => setEmail(e.target.value)} required />
                                <br />
                                <label htmlFor="jtitle" className='form_label'>Job Title</label>
                                <br />
                                <input type="text"  id="jtitle" className='form-inputs' value={jobTitle} onChange={(e) => setJobTitle(e.target.value)} required />
                                <br />
                                <label htmlFor="sal" className='form_label'>Salary</label>
                                <br />
                                <input type="number"  id="sal" className='form-inputs' value={salary} onChange={(e) => setSalary(e.target.value)} required />
                                <br />
                                <button type="submit" className='save-btn'>Update</button>
                                <button className='back-btn' onClick={handleBack}>Cancel</button>
                            </form>
                        </div>

                    </div>
                }
                {add &&
                    <div className='form-elements'>
                        <div className='dashboard-content-header'>
                            <h2>Add Staff</h2>
                        </div>
                        <div>
                            <form onSubmit={handleAddSubmit}>
                                <label htmlFor="sname" className='form_label'>Name</label>
                                <br />
                                <input type="text"  id="sname" className='form-inputs' value={name} onChange={(e) => setName(e.target.value)} required />
                                <br />
                                <label htmlFor="sage" className='form_label'>Age</label>
                                <br />
                                <input type="number"  id="sage" className='form-inputs' value={age} onChange={(e) => setAge(e.target.value)} required />
                                <br />
                                <label htmlFor="gen" className='form_label'>Gender</label>
                                <br />
                                <input type="text"  id="gen" className='form-inputs' value={gender} onChange={(e) => setGender(e.target.value)} required />
                                <br />
                                <label htmlFor="addr" className='form_label'>Address</label>
                                <br />
                                <input type="text"  id="addr" className='form-inputs' value={address} onChange={(e) => setAddress(e.target.value)} required />
                                <br />
                                <label htmlFor="ph" className='form_label'>Phone</label>
                                <br />
                                <input type="text"  id="ph" className='form-inputs' value={phone} onChange={(e) => setPhone(e.target.value)} required />
                                <br />
                                <label htmlFor="em" className='form_label'>Email</label>
                                <br />
                                <input type="text"  id="em" className='form-inputs' value={email} onChange={(e) => setEmail(e.target.value)} required />
                                <br />
                                <label htmlFor="jtitle" className='form_label'>Job Title</label>
                                <br />
                                <input type="text"  id="jtitle" className='form-inputs' value={jobTitle} onChange={(e) => setJobTitle(e.target.value)} required />
                                <br />
                                <label htmlFor="sal" className='form_label'>Salary</label>
                                <br />
                                <input type="text"  id="sal" className='form-inputs' value={salary} onChange={(e) => setSalary(e.target.value)} required />
                                <br />
                                <button type="submit" className='save-btn'>Save</button>
                                <button className='back-btn' onClick={handleBack}>Cancel</button>
                            </form>
                        </div>
                    </div>
                }
            </div>
        </div>
    )
}

export default Staff






// import React from 'react'
// import NotificationIcon from '../../assets/icons/notification.svg';
// import SettingsIcon from '../../assets/icons/settings.svg';
// import '../styles.css';
// const staff = () => {
//     return (
//         <div>
//             <div className='dashboard-content'>
//                 <div className='dashbord-header-container'>
//                     <button className='dashbord-header-btn'>New staff</button>
//                     <div className='dashbord-header-right'>
//                         <img
//                             src={NotificationIcon}
//                             alt='notification-icon'
//                             className='dashbord-header-icon' />
//                         <img
//                             src={SettingsIcon}
//                             alt='settings-icon'
//                             className='dashbord-header-icon' />
//                         <img
//                             className='dashbord-header-avatar'
//                             src='https://reqres.in/img/faces/9-image.jpg' />
//                     </div>
//                 </div>
//                 <div className='dashboard-content-container'>
//                     <div className='dashboard-content-header'>
//                         <h2>staff List</h2>
//                         <div className='dashboard-content-search'>
//                             <input
//                                 type='text'
//                                 placeholder='Search..'
//                                 className='dashboard-content-input'
//                             />
//                         </div>
//                     </div>
//                     <table>
//                         <thead>
//                             <th>ID</th>
//                             <th>NAME</th>
//                             <th>AGE</th>
//                             <th>GENDER</th>
//                             <th>ADDRESS</th>
//                             <th>PHONE</th>
//                             <th>EMAIL</th>
//                             <th>MEDICAL HISTORY</th>
//                             <th>TREATMENT PLAN</th>
//                             <th>ACTIONS</th>
//                         </thead>
//                     </table>
//                 </div>
//             </div>
//         </div>
//     )
// }

// export default staff

// import React from 'react'
// import DashboardHeader from '../../components/DashboardHeader'
// import '../styles.css';
// const Staff = () => {
//   return (
//     <div>
//         <div className='dashboard-content'>
//             <DashboardHeader
//                 btnText="New Staff" />
//             <div className='dashboard-content-container'>
//                 <div className='dashboard-content-header'>
//                     <h2>Staff List</h2>
//                     <div className='dashboard-content-search'>
//                         <input
//                             type='text'
//                             placeholder='Search..'
//                             className='dashboard-content-input'
//                            />
//                     </div>
//                 </div>
//                 <table>
//                     <thead>
//                         <th>ID</th>
//                         <th>NAME</th>
//                         <th>AGE</th>
//                         <th>GENDER</th>
//                         <th>ADDRESS</th>
//                         <th>PHONE</th>
//                         <th>JOB TITLE</th>
//                         <th>EMAIL</th>
//                         <th>SALARY</th>
//                         <th>BENIFITS</th>
//                         <th>ACTIONS</th>
//                     </thead>
//                 </table>
//             </div>
//         </div>
//     </div>
//   )
// }

// export default Staff