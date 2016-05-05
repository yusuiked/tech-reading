// Promise を使った非同期処理
function asyncProc(value) {
  return new Promise((resolve, reject) => {
    // 1000 ミリ秒後に引数 value の値を判定
    setTimeout(() => {
      if (value < 100) {
        resolve(`値は${value}です!`);
      } else {
        reject(`エラーです。${value}`);
      }
    }, 1000);
  });
}

// 非同期処理を実行
asyncProc(50)
  // then 関数でコールバック関数を同期的に記述できる
  // これができないとコールバックの入れ子が発生しコールバック地獄になる
  .then(
    // 成功コールバック関数
    response => {
      console.log(response);
    },
    // 失敗コールバック関数
    error => {
      console.log(error);
    }
  );  // ここをさらに .then で複数の非同期処理を繋げて書ける

// Promise.all を使うと複数の非同期処理をまとめて実行し、すべての結果が得られた時点で後続の処理を実行する、
// という処理を簡潔に書ける
Promise.all([
  asyncProc(50),
  asyncProc(30),
  asyncProc(10),
  asyncProc(20),
  asyncProc(80)
])
.then(
  // すべての処理が成功した時に実行
  response => {
    console.log(response);
  },
  // 一つでも処理が失敗した場合に実行
  error => {
    console.log(error);
  }
);
