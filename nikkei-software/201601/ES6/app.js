'use strict';

var _Nikkei = require('./Nikkei');

var Nikkei = _interopRequireWildcard(_Nikkei);

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } else { var newObj = {}; if (obj != null) { for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key]; } } newObj.default = obj; return newObj; } }

var animal = new Nikkei.Animal('きら', 'メス');

console.log(animal.toString());
