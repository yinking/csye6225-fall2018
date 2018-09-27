<template>
    <div>
        Students:
        <ul>
            <li v-for="student in students" :key="student.id">
                {{student.username}}
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        data() {
            return {students: []}
        },
        mounted() {
            fetch('http://localhost:8080/student', {credentials: 'include'})
                .then(response => {
                    return response.json();
                }).then(students => {
                this.students = students;
            });
        }
    }
</script>

<style scoped>
</style>
