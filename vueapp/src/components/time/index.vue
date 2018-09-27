<template>
    <div>
        Date:
        {{date}}
        <br/>
        Students:
        <ul>
            <li v-for="student in students" :key="student.id">
                name:{{student.username}}
                password:{{student.password}}
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        data() {
            return {students: [], date: null}
        },
        mounted() {
            fetch('http://localhost:8080/student', {credentials: 'include'})
                .then(response => {
                    return response.json();
                }).then(data => {
                this.date = data.date;
                this.students = data.students;
            });
        }
    }
</script>

<style scoped>
</style>
