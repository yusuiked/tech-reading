let mail = 'yukung@example.com';
// RegExp オブジェクト
let mail_pattern = /^([A-Z0-9]{1}[A-Z0-9_.-]*)@([A-Z0-9_.-]{1,}\.[A-Za-z0-9]{1,})$/i;

let [, local, domain] = mail_pattern.exec(mail);

console.log(local); // -> yukung
console.log(domain);  // -> example.com
