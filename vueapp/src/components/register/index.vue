<template>
    <div>
        <Input v-model="username" placeholder="username"/>
        <Input v-model="password" placeholder="password"/>
        <button v-on:click="register()">Register</button>
        {{status}}
    </div>
</template>

<script>
    export default {
        data() {
            return {username: null, password: null,status:null}
        },
        methods: {
            register() {
                fetch('http://localhost:8080/student/register', {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json; charset=utf-8",
                    },
                    credentials: 'include',
                    body: JSON.stringify({username: this.username, password: this.password}),
                }).then(response => {
                    return response.json();
                }).then(status => {
                    this.status=status;
                });
            }
        }
    }
</script>

<style scoped>
</style>
