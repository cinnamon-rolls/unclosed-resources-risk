# unclosed-resources-risk
This is to illustrate a way to accidentally leave a resource unclosed in Java, even if you use a try-with-resources block

Consider the following Java code:

```
try (EvilResource resource = new EvilResource()) {
  resource.doThings();
} catch (IOException e) {
  System.out.println("Catch: " + e.getMessage());
}
```

```
public EvilResource() throws IOException {
  System.out.println("I am constructing...");
  
  // open another resource, such as an InputStream
  // store a reference to that data to close it later in #close()
  // e.g. this.childStream1 = new BufferedInputStream(...);
  
  // an exception of some kind is thrown
  throw new IOException("Uh oh! (constructor)");
}
```

If the `EvilResource` constructor throws an `Exception`, then the program will not crash, but the `EvilResource#close()` method is not called. Clearly, the author intended to close the resource. This results in a subtle risk for the program to leave uncaught resources.

All code is under the Unlicense, so use it for whatever you'd like.
