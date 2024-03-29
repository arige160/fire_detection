// executes on a seperate thread

const wot = "Web-of-Things";
const assets = [
    "/",
    "/index.html",
    "/images/logo.png"
];

self.addEventListener("install", installEvent => {
    installEvent.waitUntil(
        caches.open(wot).then(cache => {
            cache.addAll(assets);
        })
    );
});
self.addEventListener("fetch", fetchEvent => {
    fetchEvent.respondWith(
        caches.match(fetchEvent.request).then(res => {
            return res || fetch(fetchEvent.request);
        })
    );
});