#ifndef CC_ARRAY_CONTAINS_H
#define CC_ARRAY_CONTAINS_H

#include "cc_common_contains.h"
#include "cc_iter_contains.h"

typedef unsigned char *uchar_ptr;
typedef unsigned char Byte;
enum
{
    CC_ARRAY_INDEX_OUT_OF_RANGR = 0xFFD0,
};

/*
 *
 *   Gerneral Array addmit from different lengths
 *
 */
struct cc_array
{
    uchar_ptr array;  // Array-address数组地址
    size_t elem_nums; // Array-contains 数组容量
    size_t elem_size; // typeSize - 接受的数据类型大小？
};

#ifndef NO_MALLOC

// Init Interface - 初始化数组接口·
int cc_array_new(struct cc_array **self, size_t elem_nums, size_t elem_size);
// free array of all memory.
int cc_array_delete(struct cc_array *self);
// free array of all memory but keep data.
int cc_array_delete_keep_data(struct cc_array *self);

#endif // NO_MALLOC

int cc_array_init(struct cc_array *self, uchar_ptr array, size_t elem_nums, size_t index, void *result);

// These functions are unsafe, because they do not check the index.
// Maybe it will skip out of boundaries.

void cc_array_get_unsafe(struct cc_array *self, size_t index, void *result);
void cc_array_set_unsafe(struct cc_array *self, size_t index, void *data);
void cc_array_ret_unsafe(struct cc_array *self, size_t indec, void **ret);

// There are safe functions.
int cc_array_get(struct cc_array *self, size_t index, void *result);
int cc_array_set(struct cc_array *self, size_t index, void *data);
int cc_array_get_ref(struct cc_array *self, size_t index, void **ret);

/// Check Whether the index has exceeded the boundaryof the array.
int cc_array_is_valid_index(struct cc_array cmp, size_t index);
int cc_array_reverse(struct cc_array *self, size_t start, size_t end);
void cc_array_swap(struct cc_array *self, size_t i, size_t j);
int cc_array_cmp(struct cc_array *self, size_t i, size_t j);

//------------------------------------

//---------------------
// The Iterator for the generic array.
// traverse the General array.

//-------------------------------

struct cc_array_iter
{
    struct cc_iter_i *iterator;
    struct cc_array *data;
    size_t cursor;
};
// Init Iteratop --- array .初始化泛型数组的迭代器
int cc_array_iter_new(struct cc_array_iter *self, struct cc_array *data);
int cc_array_iter_next(struct cc_array_iter *self, void **item, size_t *index);
#endif // CC_ARRAY_CONTAINS_h