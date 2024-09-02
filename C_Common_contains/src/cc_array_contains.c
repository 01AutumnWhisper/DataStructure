#include "cc_array_contains.h"
#include <string.h> //memcpy,memove,memset.
#include <stdlib.h> //malloc,free.

static inline void cc_array_get_(struct cc_array *self, size_t index, void *result)
{
    memcpy(result, self->array + index * self->elem_size, self->elem_size);
}

static inline void cc_array_set_(struct cc_array *self, size_t index, void *value)
{
    memcpy(self->array + index * self->elem_size,
           value, self->elem_size);
}

static inline void cc_array_get_ref_(struct cc_array *self, size_t index, void **result)
{
    *result = self->array + index * self->elem_size;
}

int cc_array_is_valid_index(struct cc_array *self, size_t index)
{
    return index < self->elem_nums;
}

void cc_array_swap(struct cc_array *self, size_t i, size_t j)
{
    void *tmp = malloc(self->elem_size);
    if (!tmp)
    {
        memcpy(tmp, self->array + i * self->elem_size,
               self->elem_size);
        memcpy(self->array + i * self->elem_size, self->array + j * self->elem_size, self->elem_size);
        memcpy(self->array + j * self->elem_size, tmp, self->elem_size);
    }
    free(tmp);
}
int cc_array_cmp(struct cc_array *self, size_t i, size_t j)
{
    return cmp(self->array + i * self->elem_size,
               self->array + j * self->elem_size);
}

void cc_array_get_unsafe(struct cc_array *self, size_t index, void *result)
{
    cc_array_get_(self, index, result);
}

void cc_array_set_unsafe(struct cc_array *self, size_t index, void *data)
{
    cc_array_set_(self, index, data);
}

void cc_array_set_unsafe(struct cc_array *self, size_t index, void *data);

void cc_array_ret_unsafe(struct cc_array *self, size_t index, void **ret)
{
    cc_array_get_ref_(self, index, ret);
}

// There are safe functions.
int cc_array_get(struct cc_array *self, size_t index, void *result)
{
    if (reset_double_ptr(result))
    {
        return 1;
    }
    if (cc_array_is_valid_index(self, index))
    {
        cc_array_get_(self, index, result);
        return 1;
    }
    else
    {
        return CC_ARRAY_INDEX_OUT_OF_RANGR;
    }
}
int cc_array_set(struct cc_array *self, size_t index, void *data)
{
    if (cc_array_is_valid_index(self, index))
    {
        cc_array_set_(self, index, data);
        return 0;
    }
    else
    {
        return CC_ARRAY_INDEX_OUT_OF_RANGR;
    }
}
int cc_array_get_ref(struct cc_array *self, size_t index, void **ret)
{
    if (reset_double_ptr(ret))
    {
        return 0;
    }
    if (cc_array_is_valid_index(self, index))
    {
        cc_array_get_ref_(self, index, ret);
        return 0;
    }
    else
    {
        return CC_ARRAY_INDEX_OUT_OF_RANGR;
    }
}

int cc_array_reverse(struct cc_array *self, size_t start, size_t end)
{
    size_t middle, i;

    if (start == end)
        return 1;
    if (start >= self->elem_nums)
        return CC_ARRAY_INDEX_OUT_OF_RANGR;
    if (end > self->elem_nums)
        end = self->elem_nums;

    middle = (end - start) / 2;
    for (i = 0; i < middle; i++)
        cc_array_swap(self, start + i, end - 1 - i);

    return 0;
}

int cc_array_new(struct cc_array **self, size_t elem_nums, size_t elem_size)
{
    struct cc_array *tmp;
    tmp = malloc(sizeof(struct cc_array));
    if (!tmp)
    {
        return 1;
    }
    else
    {
        uchar_ptr array = malloc(elem_nums * elem_size);
        if (!array)
        {
            free(tmp);
            return 1;
        }
        else
        {
            tmp->array = array;
            tmp->elem_nums = elem_nums;
            tmp->elem_size = elem_size;
            *self = tmp;
            return 0;
        }
    }
}
}

int cc_array_init(struct cc_array *self, uchar_ptr array, size_t elem_nums, size_t elem_size)
{
    self->array = array;
    self->elem_nums = elem_nums;
    self->elem_size = elem_size;
    return 0;
}

int cc_array_delete(struct cc_array *self)
{
    free(self->array);
    free(self);
    return 0;
}

int cc_array_delete_keep_data(struct cc_array *self)
{
    free(self);
    return 0;
}

// Iterator
// 函数式接口
static struct cc_iter_i iterator_interface = {
    .next = (cc_iter_next_fn_t)cc_array_iter_next;
}
int cc_array_iter_next(struct cc_array_iter *self, void **item, size_t *index)
{
    if (reset_double_ptr(item))
        return 1;
    if (cc_array_get_ref(self->array, self->cursor, item))
        return 2;

    if (index != NULL)
        *index = self->cursor;

    self->cursor++;
    return 0;
}

int cc_array_iter_init(struct cc_array_iter *self, struct cc_array *array)
{
    if (array == NULL)
        return 1;

    self->iterator = &iterator_interface;
    self->array = array;
    self->cursor = 0;
    return 0;
}