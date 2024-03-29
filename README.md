# Book-API
```
@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/resource")
    public ResponseEntity<Resource> getResource() {
        Resource resource = myService.generateResource();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=myfile.txt");
        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
}
```

or


```
@GetMapping("/{id}")
public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
    Employee emp = employeeService.findEmployeeById(id);
    if (emp != null) {
        return ResponseEntity.ok(emp); // 200 OK
    } else {
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}
```


# proper way for response
