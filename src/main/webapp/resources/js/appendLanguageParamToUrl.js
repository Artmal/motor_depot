function appendParameters() {
    var separator = (window.location.href.indexOf("?")===-1)?"?":"&";
    if (/language/.test(window.location.href)) {
        if(/language=ru/.test(window.location.href)) {
            window.location.href = window.location.href.replace("ru", "en");
        } else if(/language=en/.test(window.location.href)) {
            window.location.href = window.location.href.replace("en", "ru");
        }
    } else {
        window.location.href = window.location.href + separator + "language=ru";
    }
}
