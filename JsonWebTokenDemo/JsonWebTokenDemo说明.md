## JsonWebToken(JWT) Demo

### 官网：jwt.io

### 为什么要使用jwt

1.传统的登录是使用session，cookie技术保存用户的登录状态。session是保存在服务端的，用户使用cookie中保存的session_id向服务端请求数据。那么这里会有一个问题，当用户使用不同的浏览器访问，那么不就得重新登录？又或者当服务端需要增加服务器，当请求发送到的服务器没有保存相应的session数据，那还是得重新登录。

2.使用jwt的原因就在于解决跨域的数据共享问题。

### Json Web Token（JWT) 机制

jwt是一种**紧凑**且**自包含**的，用于在多方传递json对象的技术。传递的数据可以使用数字签名增加其安全性。可以使用hmac加密算法或rsa公钥/私钥加密方式

**紧凑**：数据小，可以通过url,post参数，请求头发送。
**自包含**:使用payload数据库记录用户必要且不隐私的数据，可以有效减少数据库访问次数。

流程如下：

用户登录认证成功后，服务端会返回token给用户，用户保存该token到本地，接下来的请求都会携带上该token。token相当于一块令牌，只有持有该令牌，你才能请求做了登录限制的接口。


### jwt数据结构

jwt的数据结构是：A.B.C格式的字符串，由'.'来分隔三部分数据。

A-header头信息

B-payload(有效荷载)，记录用户必要且不隐私的数据

C-Signature，签名

header:

- 结构：{"alg":"加密算法名称","typ":"JWT"}

payload:

payload主要分为三部分，分别是：已注册信息（registered laims),公开数据（public claims)，私有数据（private claims)

payload中常用信息有：iss(发行者);exp(到期时间），sub(主题）,aud(受众）等。这里列举的都是已注册信息。

公开数据部分一般都会在jwt注册表中增加定义。避免和已注册信息冲突。

公开数据和私有数据可以有程序员任意定义。

Signature:
	
签名信息。将header和payload进行加密，并使用点进行连接。如：加密后的head,加密后的payload。在使用相同的加密算法，对加密后的数据和签名信息进行加密，得到最终结果。

### demo

- 1.编写代码，详见src/main/java/com.jwt.demo
- 2.测试接口/login,表单包含两个数据，name=root,password=123456
- 3.测试接口/testJWT，查看响应的数据