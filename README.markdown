# DigiTrust Identity Core for Java

## What is this?

A reference library for the creation and encoding/decoding of the [DigiTrust](http://www.digitru.st/) anonymous identifier.

## Who is this for?

Platform members of the DigiTrust organization who are interested in embedded DigiTrust identity into their java-based software systems.

## How can I use it?

### Dependencies

* Java 8
* jackson-core and jackson-databind

### Maven

```
<dependency>
  <groupId>st.digitru</groupId>
  <artifactId>identity-core</artifactId>
  <version>1.0.1</version>
</dependency>
```

See the `MultithreadIdentityProducer` class to create IDs, the `IdentityCookieSerializer` class to write cookies, and the `IdentityCookieDeserializer` to read cookies.

The wiki doc [Cookies for Platforms](https://github.com/digi-trust/dt-cdn/wiki/Cookies-for-Platforms) describes the object model and encoding format implemented here.
