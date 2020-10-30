# Matrix_UI
 
Matrix structure:
    IVECTOR(1): {v11, v12, ... , v1n}
    IVECTOR(2): {v21, v22, ...., v2n}
    ...
    IVECTOR(m): {vm1, vm2, ... , vmn}

The strategy of filling in the matrix:
    Stage 1: Fill in as a sparse matrix with sparse vectors.
        You must fill in every odd element in each vector
        Note: control the number of filled cells
        Note: If a matrix has only SpareVectors -> it is SpareMatrix
        Examples:
            |0|1|2|3|       |0|1|2|3|4|
            {0 ? 0 ?}       {0 ? 0 ? 0}  ---> this vectors are the SparseVectors for the build strategy
            {0 ? 0 ?}       {0 ? 0 ? 0}
            {0 ? 0 ?}       {0 ? 0 ? 0}
            {0 ? 0 ?}       {0 ? 0 ? 0}


    Stage 2: Fill in the remaining elements (not zero):
        Fill in the remaining cells until the number of allowed cells != 0
        Note: if the user tries to fill in the SpareMatrix with too many non-zero elements, an exception will appear.
        Examples:
            |0|1|2|3|       |0|1|2|3|4|
            {* ? * ?}       {* ? * ? *} ---> this vectors are the NormalVectors for the build strategy
            {* ? * ?}       {* ? * ? *}
            {* ? 0 ?}       {* ? * ? *}
            {0 ? 0 ?}       {* ? 0 ? 0}

The matrix is rendered in HomeController.
To do this, the button handlers have an algorithm:
    1) Create a matrix (of the correct type)
    2) Initializing the matrix using the InitiatorMatrix
    3) Putting the matrix in the decorator
    4) Drawing the matrix:
        a) Checking input conditions from the UI
        b) Calling the draw method for the matrix (passing the IMatrixDrawer object)
        c) Delegating the matrix display to the IMatrixDrawer object
        d) Executing the redefined drawing method