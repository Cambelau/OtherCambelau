using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using DataAccessLayer;

namespace BusinessLogicLayer
{
    public class Business_Form1:Data_Access
    {
        public Business_Form1()
        {
            base.Link();
        }
        public DataTable Select()
        {
            string Query = "Select * from Student";
            DataTable outPut = base.SelectData(Query);

            return outPut;
        }
        public DataTable SelectGrade()
        {
            string Query = "Select * from Grade";
            DataTable outPut = base.SelectData(Query);

            return outPut;
        }

        public DataTable SelectGradeOfStudent(string studentId)
        {
            string Query = $"Select * from Grade where StudentId='{studentId}'";
            DataTable outPut = base.SelectData(Query);

            return outPut;
        }
        public DataTable SelectGradeOnId(string studentId,string courseID)
        {
            string Query = $"Select * from Grade where StudentId='{studentId}' and CourseID='{courseID}'";
            DataTable outPut = base.SelectData(Query);

            return outPut;
        }

        public DataTable SelectCourseIdOnName(string courseName)
        {
            string Query = $"Select * from Course where CoursName='{courseName}'";
            DataTable outPut = base.SelectData(Query);

            return outPut;
        }
        public DataTable SelectCourse()
        {
            string Query = "Select * from Course";
            DataTable outPut = base.SelectData(Query);

            return outPut;
        }

        public DataTable SelectCourseElement(string CourseName)
        {
            string Query = $"SELECT G.StudentID, S.Name, S.Family, G.Grade FROM Grade AS G INNER JOIN Student " +
                $"AS S ON S.StudentID = G.StudentID INNER JOIN Course " +
                $"AS C ON C.CoursID = G.CourseID WHERE C.CoursName = '{CourseName}'";
            DataTable outPut = base.SelectData(Query);

            return outPut;
        }

        public DataTable SelectOnId(int studentId)
        {
            string Query = $"Select * from Student where StudentId='{studentId}'";
            DataTable outPut = base.SelectData(Query);

            return outPut;
        }

        public DataTable SelectMinId()
        {
            string Query = "Select MIN(StudentId) from Student";
            DataTable outPut = base.SelectData(Query);

            return outPut;
        }


        public void AddData(string id,string name,string family,string birthdate)
        {
            string strsql = $"Insert into Student VALUES ('{id}' , '{name}', '{family}', '{birthdate}')";
            addData(strsql);   
        }

        public void AddGradeData(string studentId, string courseId, string grade)
        {
            string strsql = $"Insert into Grade VALUES ('{studentId}' , '{courseId}', '{grade}')";
            addData(strsql);
        }

        public void UpdateGradeData(string studentId, string courseId, string grade)
        {
            string strsql = $"Update Grade SET StudentID='{studentId}', CoursID='{courseId}', Grade='{grade}' where StudentID='{studentId}' and CoursID ='{courseId}'";
            updateData(strsql);

        }

        public void UpdateData(string id, string name, string family, string birthdate)
        {
            string strsql = $"Update Student SET Name='{name}', Family='{family}', StudentID='{id}', BirthDate='{birthdate}' where StudentID={id}";
            updateData(strsql);

        }
        public void DeleteData(string id)
        {
            string strsql = $"Delete from Student where StudentId='{id}'";
            deleteData(strsql);
        }
        public void deco()
        {
            base.unLink();
        }

    }
}
