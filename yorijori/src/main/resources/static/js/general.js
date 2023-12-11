function page_move(url) {
    var form = document.createElement("form");

    form.action = url;
    form.method = "post";

    document.body.appendChild(form);
    form.submit();
}