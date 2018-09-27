import time from "../components/time/index.vue";
import register from "../components/register/index.vue";

export default [
    {
        path: '/',
        redirect: '/time'
    },
    {
        path: "/time",
        component: time
    },
    {
        path: "/student/register",
        component: register
    }
];