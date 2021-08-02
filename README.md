Link for MySQL Script: https://drive.google.com/file/d/1i9CkgQF-ccqOz3f9RloAwXu0sViTaABj/view?usp=sharing


Method for Testing 

Get All List (GET METHOD)
            http://localhost:8080/api/tasks

Get Completed Tasks (GET METHOD)
            http://localhost:8080/api/tasks/completedTasks

Get Incomplete Tasks (GET METHOD)
            http://localhost:8080/api/tasks/incompleteTasks

Get Task By Id 
            http://localhost:8080/api/tasks/{id}


Add A Task (POST METHOD)
            http://localhost:8080/api/tasks

            Body > Raw > JSON 
             {
                "title": "Buy Food",
                "description": "From BHX",
                "status": 0,
                "note": "on 4/8/21"     
             }

Update A Task (PUT METHOD)
            http://localhost:8080/api/tasks

            Body > Raw > JSON 
             {
                "title": "Buy Food",
                "description": "From BHX",
                "status": 1,
                "note": "over budget"
             }
             
Update Satus of Task (PUT METHOD)
            http://localhost:8080/api/tasks/12
Request Param: [Id : 1]


Delete a Task (DELETE METHOD)
            http://localhost:8080/api/tasks/12


