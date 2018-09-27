<template>
    <div>
        <Input v-model="username" placeholder="username"/>
        <button v-on:click="register()">Register</button>
    </div>
</template>

<script>
    export default {
        data() {
            return {username: null, password: null}
        },
        methods: {
            register() {
                fetch('http://localhost:8080/student/register', {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json; charset=utf-8",
                    },
                    credentials: 'include',
                    body: JSON.stringify({username: this.username}),
                })
            }
        }
    }
</script>

<style scoped>
</style>
