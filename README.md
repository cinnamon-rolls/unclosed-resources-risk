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

If the EvilResource constructor throws an Exception, then the program will not crash, but the close() method is not called. Clearly, the author intended to close the resource.

All code is under the Unlicense, so use it for whatever you'd like.
